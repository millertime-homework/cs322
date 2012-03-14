	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 3) (NAME wSZ))))]
	mov 3,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 2)]
	mov 2,%l0
	st %l0,[%l1]
! [MOVE (TEMP 2) (BINOP + (TEMP 1) (BINOP * (CONST 2) (NAME wSZ)))]
	mov %l1,%l0
	mov 2,%l2
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
! [CJUMP < (VAR 2) (CONST 2) (NAME L3)]
	ld [%fp-8],%l0
	mov 2,%l4
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
! [CALLST (NAME print) ( (MEM (BINOP + (VAR 1) (BINOP * (BINOP + (VAR 2) (CONST 1)) (NAME wSZ)))))]
