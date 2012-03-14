	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 2) (NAME wSZ))))]
	mov 2,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (MEM (BINOP + (TEMP 1) (NAME wSZ))) (CONST 0)]
	mov 0,%l0
	mov %l1,%l2
	mov 4,%l3
	add %l2,%l3,%l2
	st %l0,[%l2]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 7) (CALL (NAME A_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call A_go
	nop
	mov %o0,%l2
!>> Temp t7 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 7))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
A_go:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (BINOP * (CONST 5) (NAME wSZ))))]
	mov 5,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 2)) (CONST 4)]
	mov 4,%l0
	st %l0,[%l1]
! [MOVE (TEMP 3) (BINOP + (TEMP 2) (BINOP * (CONST 4) (NAME wSZ)))]
	mov %l1,%l0
	mov 4,%l2
	mov 4,%l3
	smul %l2,%l3,%l2
	mov %l2,%l2
	add %l0,%l2,%l0
	mov %l0,%l0
!>> Temp t3 assigned to reg %l2
	mov %l0,%l2
! [LABEL L0]
L0:
! [MOVE (MEM (TEMP 3)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l2]
! [MOVE (TEMP 3) (BINOP - (TEMP 3) (NAME wSZ))]
	mov %l2,%l0
	mov 4,%l3
	sub %l0,%l3,%l0
	mov %l0,%l0
	mov %l0,%l2
! [CJUMP > (TEMP 3) (TEMP 2) (NAME L0)]
	mov %l2,%l0
	mov %l1,%l3
	cmp %l0,%l3
	bg L0
	nop
! [MOVE (VAR 1) (TEMP 2)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 4) (CONST 1)]
	mov 1,%l0
!>> Temp t4 assigned to reg %l3
	mov 1,%l3
! [CJUMP < (CONST 1) (CONST 2) (NAME L1)]
	mov 1,%l0
	mov 2,%l4
	cmp %l0,%l4
	bl L1
	nop
! [MOVE (TEMP 4) (CONST 0)]
	mov 0,%l0
	mov 0,%l3
! [LABEL L1]
L1:
! [MOVE (TEMP 5) (CONST 1)]
	mov 1,%l0
!>> Temp t5 assigned to reg %l4
	mov 1,%l4
! [CJUMP > (CONST 3) (CONST 4) (NAME L2)]
	mov 3,%l0
	mov 4,%l5
	cmp %l0,%l5
	bg L2
	nop
! [MOVE (TEMP 5) (CONST 0)]
	mov 0,%l0
	mov 0,%l4
! [LABEL L2]
L2:
! [MOVE (TEMP 6) (CONST 1)]
	mov 1,%l0
!>> Temp t6 assigned to reg %l5
	mov 1,%l5
! [CJUMP == (CONST 5) (BINOP + (CONST 6) (BINOP * (CONST 7) (CONST 8))) (NAME L3)]
	mov 5,%l0
	mov 6,%l7
	mov 7,%i1
	mov 8,%i2
	smul %i1,%i2,%i1
	mov %i1,%i1
	add %l7,%i1,%l7
	mov %l7,%l6
	cmp %l0,%l6
	be L3
	nop
! [MOVE (TEMP 6) (CONST 0)]
	mov 0,%l0
	mov 0,%l5
! [LABEL L3]
L3:
! [MOVE (FIELD (PARAM 0) 0) (BINOP || (BINOP || (TEMP 4) (BINOP && (TEMP 5) (TEMP 6))) (CONST 0))]
	mov %l3,%l0
	mov %l4,%l6
	mov %l5,%l7
	and %l6,%l7,%l6
	mov %l6,%l6
	or %l0,%l6,%l0
	mov %l0,%l0
	mov 0,%l6
	or %l0,%l6,%l0
	mov %l0,%l0
	ld [%fp+68],%l6
	st %l0,[%l6]
! [MOVE (FIELD (PARAM 0) 1) (BINOP + (BINOP + (BINOP - (CONST 8) (CONST 7)) (CONST 6)) (BINOP / (BINOP * (CONST 5) (MEM (VAR 1))) (CONST 2)))]
	mov 8,%l0
	mov 7,%l7
	sub %l0,%l7,%l0
	mov %l0,%l0
	mov 6,%l7
	add %l0,%l7,%l0
	mov %l0,%l0
	mov 5,%l7
