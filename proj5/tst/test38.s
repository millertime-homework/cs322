	.global main
	.align 4
main:
!locals=2, max_args=3
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 11) (NAME wSZ))))]
	mov 11,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 10)]
	mov 10,%l0
	st %l0,[%l1]
! [MOVE (TEMP 2) (BINOP + (TEMP 1) (BINOP * (CONST 10) (NAME wSZ)))]
	mov %l1,%l0
	mov 10,%l2
	mov 4,%l3
	smul %l2,%l3,%l2
	mov %l2,%l2
	add %l0,%l2,%l0
	mov %l0,%l0
!>> Temp t2 assigned to reg %l2
	mov %l0,%l2
! [LABEL L0]
L0:
! [MOVE (MEM (TEMP 2)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l2]
! [MOVE (TEMP 2) (BINOP - (TEMP 2) (NAME wSZ))]
	mov %l2,%l0
	mov 4,%l3
	sub %l0,%l3,%l0
	mov %l0,%l0
	mov %l0,%l2
! [CJUMP > (TEMP 2) (TEMP 1) (NAME L0)]
	mov %l2,%l0
	mov %l1,%l3
	cmp %l0,%l3
	bg L0
	nop
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (VAR 2) (CONST 0)]
	mov 0,%l0
	st %l0,[%fp-8]
! [LABEL L1]
L1:
! [MOVE (TEMP 3) (CONST 1)]
	mov 1,%l0
!>> Temp t3 assigned to reg %l3
	mov 1,%l3
! [CJUMP < (VAR 2) (CONST 10) (NAME L3)]
	ld [%fp-8],%l0
	mov 10,%l4
	cmp %l0,%l4
	bl L3
	nop
! [MOVE (TEMP 3) (CONST 0)]
	mov 0,%l0
	mov 0,%l3
! [LABEL L3]
L3:
! [CJUMP == (TEMP 3) (CONST 0) (NAME L2)]
	mov %l3,%l0
	mov 0,%l4
	cmp %l0,%l4
	be L2
	nop
! [MOVE (MEM (BINOP + (VAR 1) (BINOP * (BINOP + (VAR 2) (CONST 1)) (NAME wSZ)))) (BINOP - (CONST 10) (VAR 2))]
	mov 10,%l0
	ld [%fp-8],%l4
	sub %l0,%l4,%l0
	mov %l0,%l0
	ld [%fp-4],%l4
	ld [%fp-8],%l5
	mov 1,%l6
	add %l5,%l6,%l5
	mov %l5,%l5
	mov 4,%l6
	smul %l5,%l6,%l5
	mov %l5,%l5
	add %l4,%l5,%l4
	st %l0,[%l4]
! [MOVE (VAR 2) (BINOP + (VAR 2) (CONST 1))]
	ld [%fp-8],%l0
	mov 1,%l4
	add %l0,%l4,%l0
	mov %l0,%l0
	st %l0,[%fp-8]
! [JUMP (NAME L1)]
	ba L1
	nop
! [LABEL L2]
L2:
! [CALLST (NAME prog_selectionSort) ( (PARAM 0) (VAR 1) (VAR 2))]
	call prog_selectionSort
	nop
! [CALLST (NAME print) ( (STRING "Your numbers in sorted order are:"))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
! [MOVE (VAR 2) (CONST 0)]
	mov 0,%l0
	st %l0,[%fp-8]
! [LABEL L4]
L4:
! [MOVE (TEMP 4) (CONST 1)]
	mov 1,%l0
!>> Temp t4 assigned to reg %l4
	mov 1,%l4
! [CJUMP < (VAR 2) (CONST 10) (NAME L6)]
	ld [%fp-8],%l0
	mov 10,%l5
	cmp %l0,%l5
	bl L6
	nop
! [MOVE (TEMP 4) (CONST 0)]
	mov 0,%l0
	mov 0,%l4
! [LABEL L6]
L6:
! [CJUMP == (TEMP 4) (CONST 0) (NAME L5)]
	mov %l4,%l0
	mov 0,%l5
	cmp %l0,%l5
	be L5
	nop
! [CALLST (NAME print) ( (MEM (BINOP + (VAR 1) (BINOP * (BINOP + (VAR 2) (CONST 1)) (NAME wSZ)))))]
